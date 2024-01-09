cmake_minimum_required(VERSION 2.8)
project(foo)

#$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

add_library(foo SHARED foo.cxx foo.h config.h.in)
#add_library(foo STATIC foo.cxx foo.h config.h.in)

#target_include_directories(foo PUBLIC "$CMAKE_CURRENT_SOURCE_DIR")

set_target_properties(foo PROPERTIES
  PUBLIC_HEADER "foo.h")

install(TARGETS foo
  # IMPORTANT: Add the foo library to the "export-set"
  EXPORT fooTargets
  RUNTIME DESTINATION "${INSTALL_BIN_DIR}" COMPONENT bin
  LIBRARY DESTINATION "${INSTALL_LIB_DIR}" COMPONENT shlib
  PUBLIC_HEADER DESTINATION "${INSTALL_INCLUDE_DIR}"
    COMPONENT dev)

#$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$


set(FOO_MAJOR_VERSION 0)
set(FOO_MINOR_VERSION 1)
set(FOO_PATCH_VERSION 0)
set(FOO_VERSION
  ${FOO_MAJOR_VERSION}.${FOO_MINOR_VERSION}.${FOO_PATCH_VERSION})

# Offer the user the choice of overriding the installation directories
set(INSTALL_LIB_DIR lib CACHE PATH "Installation directory for libraries")
set(INSTALL_BIN_DIR bin CACHE PATH "Installation directory for executables")
set(INSTALL_INCLUDE_DIR include CACHE PATH
  "Installation directory for header files")
if(WIN32 AND NOT CYGWIN)
  set(DEF_INSTALL_CMAKE_DIR CMake)
else()
  set(DEF_INSTALL_CMAKE_DIR lib/CMake/foo)
endif()
set(INSTALL_CMAKE_DIR ${DEF_INSTALL_CMAKE_DIR} CACHE PATH
  "Installation directory for CMake files")

# Make relative paths absolute (needed later on)
foreach(p LIB BIN INCLUDE CMAKE)
  set(var INSTALL_${p}_DIR)
  if(NOT IS_ABSOLUTE "${${var}}")
    set(${var} "${CMAKE_INSTALL_PREFIX}/${${var}}")
  endif()
endforeach()

# set up include-directories
include_directories(
  "${PROJECT_SOURCE_DIR}"   # to find foo/foo.h
  "${PROJECT_BINARY_DIR}")  # to find foo/config.h

# Add sub-directories
#add_subdirectory(foo)
#add_subdirectory(bar)

# The interesting stuff goes here
# ===============================

# Add all targets to the build-tree export set
#export(TARGETS foo bar
export(TARGETS foo
  FILE "${PROJECT_BINARY_DIR}/fooTargets.cmake")

# Export the package for use from the build-tree
# (this registers the build-tree with a global CMake-registry)
export(PACKAGE foo)

# Create the fooConfig.cmake and fooVersionConfig files
file(RELATIVE_PATH REL_INCLUDE_DIR "${INSTALL_CMAKE_DIR}" "${INSTALL_INCLUDE_DIR}")
# ... for the build tree
set(CONF_INCLUDE_DIRS "${PROJECT_SOURCE_DIR}" "${PROJECT_BINARY_DIR}")
configure_file(fooConfig.cmake.in  "${PROJECT_BINARY_DIR}/fooConfig.cmake" @ONLY)
# ... for the install tree
set(CONF_INCLUDE_DIRS "\${FOO_CMAKE_DIR}/${REL_INCLUDE_DIR}")
configure_file(fooConfig.cmake.in "${PROJECT_BINARY_DIR}${CMAKE_FILES_DIRECTORY}/fooConfig.cmake" @ONLY)
# ... for both
configure_file(fooVersionConfig.cmake.in "${PROJECT_BINARY_DIR}/fooVersionConfig.cmake" @ONLY)

# Install the fooConfig.cmake and fooVersionConfig.cmake
install(FILES
  "${PROJECT_BINARY_DIR}${CMAKE_FILES_DIRECTORY}/fooConfig.cmake"
  "${PROJECT_BINARY_DIR}/fooVersionConfig.cmake"
  DESTINATION "${INSTALL_CMAKE_DIR}" COMPONENT dev)

#Install the export set for use with the install-tree
install(EXPORT fooTargets DESTINATION "${INSTALL_CMAKE_DIR}" COMPONENT dev)


cmake_minimum_required(VERSION 3.10)
project(sqlite4 C)

include_directories(.)

add_library(sqlite4 SHARED sqlite4.c)


#$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

set_target_properties(sqlite4 PROPERTIES
  PUBLIC_HEADER "sqlite4.h")

install(TARGETS sqlite4
  # IMPORTANT: Add the sqlite4 library to the "export-set"
  EXPORT sqlite4Targets
  RUNTIME DESTINATION "${INSTALL_BIN_DIR}" COMPONENT bin
  LIBRARY DESTINATION "${INSTALL_LIB_DIR}" COMPONENT shlib
  PUBLIC_HEADER DESTINATION "${INSTALL_INCLUDE_DIR}"
    COMPONENT dev)

#$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
set(SQLITE4_MAJOR_VERSION 0)
set(SQLITE4_MINOR_VERSION 1)
set(SQLITE4_PATCH_VERSION 0)
set(SQLITE4_VERSION
  ${SQLITE4_MAJOR_VERSION}.${SQLITE4_MINOR_VERSION}.${SQLITE4_PATCH_VERSION})

# Offer the user the choice of overriding the installation directories
set(INSTALL_LIB_DIR lib CACHE PATH "Installation directory for libraries")
set(INSTALL_BIN_DIR bin CACHE PATH "Installation directory for executables")
set(INSTALL_INCLUDE_DIR include CACHE PATH
  "Installation directory for header files")
if(WIN32 AND NOT CYGWIN)
  set(DEF_INSTALL_CMAKE_DIR CMake)
else()
  set(DEF_INSTALL_CMAKE_DIR lib/CMake/sqlite4)
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
  "${PROJECT_SOURCE_DIR}"   # to find sqlite4.h
  "${PROJECT_BINARY_DIR}")  

# Add all targets to the build-tree export set

export(TARGETS sqlite4
  FILE "${PROJECT_BINARY_DIR}/sqlite4Targets.cmake")

# Export the package for use from the build-tree
# (this registers the build-tree with a global CMake-registry)
export(PACKAGE sqlite4)

# Create the sqlite4Config.cmake and sqlite4VersionConfig files
file(RELATIVE_PATH REL_INCLUDE_DIR "${INSTALL_CMAKE_DIR}" "${INSTALL_INCLUDE_DIR}")
# ... for the build tree
set(CONF_INCLUDE_DIRS "${PROJECT_SOURCE_DIR}" "${PROJECT_BINARY_DIR}")
configure_file(sqlite4Config.cmake.in  "${PROJECT_BINARY_DIR}/sqlite4Config.cmake" @ONLY)
# ... for the install tree
set(CONF_INCLUDE_DIRS "\${SQLITE4_CMAKE_DIR}/${REL_INCLUDE_DIR}")
configure_file(sqlite4Config.cmake.in "${PROJECT_BINARY_DIR}${CMAKE_FILES_DIRECTORY}/sqlite4Config.cmake" @ONLY)
# ... for both
configure_file(sqlite4VersionConfig.cmake.in "${PROJECT_BINARY_DIR}/sqlite4VersionConfig.cmake" @ONLY)

# Install the sqlite4Config.cmake and sqlite4VersionConfig.cmake
install(FILES
  "${PROJECT_BINARY_DIR}${CMAKE_FILES_DIRECTORY}/sqlite4Config.cmake"
  "${PROJECT_BINARY_DIR}/sqlite4VersionConfig.cmake"
  DESTINATION "${INSTALL_CMAKE_DIR}" COMPONENT dev)

#Install the export set for use with the install-tree
install(EXPORT sqlite4Targets DESTINATION "${INSTALL_CMAKE_DIR}" COMPONENT dev)


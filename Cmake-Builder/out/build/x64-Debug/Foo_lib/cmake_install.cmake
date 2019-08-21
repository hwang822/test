# Install script for directory: C:/Workarea/test/Cmake-Builder/Foo_lib

# Set the install prefix
if(NOT DEFINED CMAKE_INSTALL_PREFIX)
  set(CMAKE_INSTALL_PREFIX "C:/Workarea/test/Cmake-Builder/out/install/x64-Debug")
endif()
string(REGEX REPLACE "/$" "" CMAKE_INSTALL_PREFIX "${CMAKE_INSTALL_PREFIX}")

# Set the install configuration name.
if(NOT DEFINED CMAKE_INSTALL_CONFIG_NAME)
  if(BUILD_TYPE)
    string(REGEX REPLACE "^[^A-Za-z0-9_]+" ""
           CMAKE_INSTALL_CONFIG_NAME "${BUILD_TYPE}")
  else()
    set(CMAKE_INSTALL_CONFIG_NAME "Debug")
  endif()
  message(STATUS "Install configuration: \"${CMAKE_INSTALL_CONFIG_NAME}\"")
endif()

# Set the component getting installed.
if(NOT CMAKE_INSTALL_COMPONENT)
  if(COMPONENT)
    message(STATUS "Install component: \"${COMPONENT}\"")
    set(CMAKE_INSTALL_COMPONENT "${COMPONENT}")
  else()
    set(CMAKE_INSTALL_COMPONENT)
  endif()
endif()

# Is this installation the result of a crosscompile?
if(NOT DEFINED CMAKE_CROSSCOMPILING)
  set(CMAKE_CROSSCOMPILING "FALSE")
endif()

if("x${CMAKE_INSTALL_COMPONENT}x" STREQUAL "xdevx" OR NOT CMAKE_INSTALL_COMPONENT)
  list(APPEND CMAKE_ABSOLUTE_DESTINATION_FILES
   "C:/Workarea/test/Cmake-Builder/out/install/x64-Debug/CMake/FooBarConfig.cmake;C:/Workarea/test/Cmake-Builder/out/install/x64-Debug/CMake/FooBarVersionConfig.cmake")
  if(CMAKE_WARN_ON_ABSOLUTE_INSTALL_DESTINATION)
    message(WARNING "ABSOLUTE path INSTALL DESTINATION : ${CMAKE_ABSOLUTE_DESTINATION_FILES}")
  endif()
  if(CMAKE_ERROR_ON_ABSOLUTE_INSTALL_DESTINATION)
    message(FATAL_ERROR "ABSOLUTE path INSTALL DESTINATION forbidden (by caller): ${CMAKE_ABSOLUTE_DESTINATION_FILES}")
  endif()
file(INSTALL DESTINATION "C:/Workarea/test/Cmake-Builder/out/install/x64-Debug/CMake" TYPE FILE FILES
    "C:/Workarea/test/Cmake-Builder/out/build/x64-Debug/Foo_lib/CMakeFiles/FooBarConfig.cmake"
    "C:/Workarea/test/Cmake-Builder/out/build/x64-Debug/Foo_lib/FooBarVersionConfig.cmake"
    )
endif()

if("x${CMAKE_INSTALL_COMPONENT}x" STREQUAL "xdevx" OR NOT CMAKE_INSTALL_COMPONENT)
  if(EXISTS "$ENV{DESTDIR}C:/Workarea/test/Cmake-Builder/out/install/x64-Debug/CMake/FooBarTargets.cmake")
    file(DIFFERENT EXPORT_FILE_CHANGED FILES
         "$ENV{DESTDIR}C:/Workarea/test/Cmake-Builder/out/install/x64-Debug/CMake/FooBarTargets.cmake"
         "C:/Workarea/test/Cmake-Builder/out/build/x64-Debug/Foo_lib/CMakeFiles/Export/C_/Workarea/test/Cmake-Builder/out/install/x64-Debug/CMake/FooBarTargets.cmake")
    if(EXPORT_FILE_CHANGED)
      file(GLOB OLD_CONFIG_FILES "$ENV{DESTDIR}C:/Workarea/test/Cmake-Builder/out/install/x64-Debug/CMake/FooBarTargets-*.cmake")
      if(OLD_CONFIG_FILES)
        message(STATUS "Old export file \"$ENV{DESTDIR}C:/Workarea/test/Cmake-Builder/out/install/x64-Debug/CMake/FooBarTargets.cmake\" will be replaced.  Removing files [${OLD_CONFIG_FILES}].")
        file(REMOVE ${OLD_CONFIG_FILES})
      endif()
    endif()
  endif()
  list(APPEND CMAKE_ABSOLUTE_DESTINATION_FILES
   "C:/Workarea/test/Cmake-Builder/out/install/x64-Debug/CMake/FooBarTargets.cmake")
  if(CMAKE_WARN_ON_ABSOLUTE_INSTALL_DESTINATION)
    message(WARNING "ABSOLUTE path INSTALL DESTINATION : ${CMAKE_ABSOLUTE_DESTINATION_FILES}")
  endif()
  if(CMAKE_ERROR_ON_ABSOLUTE_INSTALL_DESTINATION)
    message(FATAL_ERROR "ABSOLUTE path INSTALL DESTINATION forbidden (by caller): ${CMAKE_ABSOLUTE_DESTINATION_FILES}")
  endif()
file(INSTALL DESTINATION "C:/Workarea/test/Cmake-Builder/out/install/x64-Debug/CMake" TYPE FILE FILES "C:/Workarea/test/Cmake-Builder/out/build/x64-Debug/Foo_lib/CMakeFiles/Export/C_/Workarea/test/Cmake-Builder/out/install/x64-Debug/CMake/FooBarTargets.cmake")
  if("${CMAKE_INSTALL_CONFIG_NAME}" MATCHES "^([Dd][Ee][Bb][Uu][Gg])$")
    list(APPEND CMAKE_ABSOLUTE_DESTINATION_FILES
     "C:/Workarea/test/Cmake-Builder/out/install/x64-Debug/CMake/FooBarTargets-debug.cmake")
    if(CMAKE_WARN_ON_ABSOLUTE_INSTALL_DESTINATION)
        message(WARNING "ABSOLUTE path INSTALL DESTINATION : ${CMAKE_ABSOLUTE_DESTINATION_FILES}")
    endif()
    if(CMAKE_ERROR_ON_ABSOLUTE_INSTALL_DESTINATION)
        message(FATAL_ERROR "ABSOLUTE path INSTALL DESTINATION forbidden (by caller): ${CMAKE_ABSOLUTE_DESTINATION_FILES}")
    endif()
file(INSTALL DESTINATION "C:/Workarea/test/Cmake-Builder/out/install/x64-Debug/CMake" TYPE FILE FILES "C:/Workarea/test/Cmake-Builder/out/build/x64-Debug/Foo_lib/CMakeFiles/Export/C_/Workarea/test/Cmake-Builder/out/install/x64-Debug/CMake/FooBarTargets-debug.cmake")
  endif()
endif()

if(NOT CMAKE_INSTALL_LOCAL_ONLY)
  # Include the install script for each subdirectory.
  include("C:/Workarea/test/Cmake-Builder/out/build/x64-Debug/Foo_lib/foo/cmake_install.cmake")

endif()


# - Config file for the FooBar package
# It defines the following variables
#  FOOBAR_INCLUDE_DIRS - include directories for FooBar
#  FOOBAR_LIBRARIES    - libraries to link against

# Compute paths
get_filename_component(FOOBAR_CMAKE_DIR "${CMAKE_CURRENT_LIST_FILE}" PATH)
set(FOOBAR_INCLUDE_DIRS "C:/Workarea/test/Cmake-Builder/Foo_lib;C:/Workarea/test/Cmake-Builder/out/build/x64-Debug/Foo_lib")

# Our library dependencies (contains definitions for IMPORTED targets)
include("${FOOBAR_CMAKE_DIR}/FooBarTargets.cmake")

# These are IMPORTED targets created by FooBarTargets.cmake
set(FOOBAR_LIBRARIES foo)

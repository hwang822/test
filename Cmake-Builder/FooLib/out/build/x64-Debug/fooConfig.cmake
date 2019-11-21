# - Config file for the foo package
# It defines the following variables
#  FOO_INCLUDE_DIRS - include directories for foo
#  FOO_LIBRARIES    - libraries to link against

# Compute paths
get_filename_component(FOO_CMAKE_DIR "${CMAKE_CURRENT_LIST_FILE}" PATH)
set(FOO_INCLUDE_DIRS "C:/Workarea/test/Cmake-Builder/FooLib;C:/Workarea/test/Cmake-Builder/FooLib/out/build/x64-Debug")

# Our library dependencies (contains definitions for IMPORTED targets)
include("${FOO_CMAKE_DIR}/fooTargets.cmake")

# These are IMPORTED targets created by fooTargets.cmake
set(FOO_LIBRARIES foo)
set(FOO_LIBRARIES_DIR "${FOO_CMAKE_DIR}")

# - Config file for the sqlite4 package
# It defines the following variables
#  SQLITE4_INCLUDE_DIRS - include directories for sqlite4
#  SQLITE4_LIBRARIES    - libraries to link against

# Compute paths
get_filename_component(SQLITE4_CMAKE_DIR "${CMAKE_CURRENT_LIST_FILE}" PATH)
set(SQLITE4_INCLUDE_DIRS "C:/Workarea/test/Cmake-Builder/sqlite4;C:/Workarea/test/Cmake-Builder/sqlite4/out/build/x64-Debug")

# Our library dependencies (contains definitions for IMPORTED targets)
include("${SQLITE4_CMAKE_DIR}/sqlite4Targets.cmake")

# These are IMPORTED targets created by sqlite4Targets.cmake
set(SQLITE4_LIBRARIES sqlite4)
set(SQLITE4_LIBRARIES_DIR "${SQLITE4_CMAKE_DIR}")

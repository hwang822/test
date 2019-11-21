# - Config file for the sqlite3 package
# It defines the following variables
#  SQLITE3_INCLUDE_DIRS - include directories for sqlite3
#  SQLITE3_LIBRARIES    - libraries to link against

# Compute paths
get_filename_component(SQLITE3_CMAKE_DIR "${CMAKE_CURRENT_LIST_FILE}" PATH)
set(SQLITE3_INCLUDE_DIRS "${SQLITE4_CMAKE_DIR}/../include")

# Our library dependencies (contains definitions for IMPORTED targets)
include("${SQLITE3_CMAKE_DIR}/sqlite3Targets.cmake")

# These are IMPORTED targets created by sqlite3Targets.cmake
set(SQLITE3_LIBRARIES sqlite3)
set(SQLITE3_LIBRARIES_DIR "${SQLITE3_CMAKE_DIR}")

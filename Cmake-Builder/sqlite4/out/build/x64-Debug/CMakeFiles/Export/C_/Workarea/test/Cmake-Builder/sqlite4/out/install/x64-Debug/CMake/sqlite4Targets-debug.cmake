#----------------------------------------------------------------
# Generated CMake target import file for configuration "Debug".
#----------------------------------------------------------------

# Commands may need to know the format version.
set(CMAKE_IMPORT_FILE_VERSION 1)

# Import target "sqlite4" for configuration "Debug"
set_property(TARGET sqlite4 APPEND PROPERTY IMPORTED_CONFIGURATIONS DEBUG)
set_target_properties(sqlite4 PROPERTIES
  IMPORTED_LOCATION_DEBUG "${_IMPORT_PREFIX}/bin/sqlite4.dll"
  )

list(APPEND _IMPORT_CHECK_TARGETS sqlite4 )
list(APPEND _IMPORT_CHECK_FILES_FOR_sqlite4 "${_IMPORT_PREFIX}/bin/sqlite4.dll" )

# Commands beyond this point should not need to know the version.
set(CMAKE_IMPORT_FILE_VERSION)

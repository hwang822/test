#----------------------------------------------------------------
# Generated CMake target import file for configuration "Debug".
#----------------------------------------------------------------

# Commands may need to know the format version.
set(CMAKE_IMPORT_FILE_VERSION 1)

# Import target "foo" for configuration "Debug"
set_property(TARGET foo APPEND PROPERTY IMPORTED_CONFIGURATIONS DEBUG)
set_target_properties(foo PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_DEBUG "C"
  IMPORTED_LOCATION_DEBUG "${_IMPORT_PREFIX}/lib/foo.lib"
  )

list(APPEND _IMPORT_CHECK_TARGETS foo )
list(APPEND _IMPORT_CHECK_FILES_FOR_foo "${_IMPORT_PREFIX}/lib/foo.lib" )

# Commands beyond this point should not need to know the version.
set(CMAKE_IMPORT_FILE_VERSION)

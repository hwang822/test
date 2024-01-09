add_executable(bar bar.c)

target_link_libraries(bar foo)

install(TARGETS bar
  # IMPORTANT: Add the bar executable to the "export-set"
  EXPORT FooBarTargets
  RUNTIME DESTINATION "${INSTALL_BIN_DIR}" COMPONENT bin)

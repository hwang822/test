cmake_minimum_required(VERSION 3.1)
project(exp)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)


#include_directories(vtkbool-master)

#add_subdirectory(vtkbool-master)

find_package(VTKBool REQUIRED)
find_package(VTK REQUIRED)
include_directories(${VTK_USE_FILE})
include_directories(${VTKBOOL_INCLUDE_DIRS})

message("----------------")
message("VTKBOOL_INCLUDE_DIRS: " ${VTKBOOL_INCLUDE_DIRS})
message("VTKBOOL_LIBRARIES: " ${VTKBOOL_LIBRARIES})
message("VTKBOOL_LIBRARIES_DIR: " ${VTKBOOL_LIBRARIES_DIR})



#add_library(exp STATIC exp.cxx)
add_executable(exp exp.cxx)
target_link_libraries(exp ${VTK_LIBRARIES})
target_link_libraries(exp ${VTKBOOL_LIBRARIES})

#add_executable(vtkbool-testing vtkbool-testing.cxx)


#target_link_libraries(vtkbool-testing ${VTK_LIBRARIES} vtkbool)

add_subdirectory(FooLib)
add_subdirectory(BarExe)




#add_custom_command(TARGET exp POST_BUILD        # Adds a post-build event to MyTest
#    COMMAND ${CMAKE_COMMAND} -E copy_if_different  # which executes "cmake - E copy_if_different..."
#        "${PROJECT_BINARY_DIR}/vtkbool-master/vtkbool.dll"      # <--this is in-file
#        ${PROJECT_BINARY_DIR})                 # <--this is out-file path

#add_custom_command(TARGET exp POST_BUILD        # Adds a post-build event to MyTest
    #COMMAND ${CMAKE_COMMAND} -E copy_if_different  # which executes "cmake - E copy_if_different..."
        #"${PROJECT_BINARY_DIR}/vtkbool-master/vtkbool.dll"      # <--this is in-file
#        ${PROJECT_BINARY_DIR}/BarExe)                 # <--this is out-file path


#configure_file(vtkTypeListMacros.h.in vtkTypeListMacros.h)
#configure_file(vtkSlicerRtCommonConfigure.h.in vtkSlicerRtCommonConfigure.h)

#add_custom_command(TARGET testing COMMAND ${CMAKE_COMMAND} -E copy_if_different vtkbool.dll  "C:\\Windows\\System32\\")   
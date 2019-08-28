cmake_minimum_required(VERSION 3.1)
project(exp)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)


include_directories(vtkbool-master)

add_subdirectory(vtkbool-master)

find_package(VTK REQUIRED COMPONENTS vtkFiltersSources NO_MODULE)
include(${VTK_USE_FILE})

include_directories(vtkbool-master/libs/vp)

add_executable(exp exp.cxx)
target_link_libraries(exp ${VTK_LIBRARIES} vtkbool)

add_custom_command(TARGET exp POST_BUILD        # Adds a post-build event to MyTest
    COMMAND ${CMAKE_COMMAND} -E copy_if_different  # which executes "cmake - E copy_if_different..."
        "${PROJECT_BINARY_DIR}/vtkbool-master/vtkbool.dll"      # <--this is in-file
        ${PROJECT_BINARY_DIR})                 # <--this is out-file path


#cmake_minimum_required(VERSION 2.8)
#project(CMake-Builder C CXX)

#add_subdirectory(FooBar)
#add_subdirectory(FooLib)
#add_subdirectory(BarExe)
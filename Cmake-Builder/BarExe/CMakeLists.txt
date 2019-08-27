cmake_minimum_required(VERSION 2.8)
project(BarEXE)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
add_definitions(-DvtkSlicerRtCommon_EXPORTS)
add_definitions(-DVTK_EXPORT)

find_package(Boost REQUIRED COMPONENTS filesystem) #remove to run Cmake for sln
find_package(VTKBool REQUIRED)
find_package(VTK REQUIRED)
find_package(foo REQUIRED)

#include_directories(${PROJECT_SOURCE_DIR})
#include_directories(${PROJECT_SOURCE_DIR}/include/sdc3dlibs)


include_directories(${VTK_USE_FILE})
include_directories(${VTKBOOL_INCLUDE_DIRS})
include_directories(${Boost_INCLUDE_DIRS})
include_directories(${VTKBOOL_INCLUDE_DIRS}/libs/vp)
include_directories(${FOO_INCLUDE_DIRS})

message("----------------")
message("VTK_USE_FILE: " ${VTK_USE_FILE})
message("VTK_LIBRARIES: " ${VTK_LIBRARIES})
message("VTKBOOL_INCLUDE_DIRS: " ${VTKBOOL_INCLUDE_DIRS})
message("VTKBOOL_LIBRARIES: " ${VTKBOOL_LIBRARIES})
message("Boost_INCLUDE_DIRS: " ${Boost_INCLUDE_DIRS})
message("Boost_LIBRARIES: " ${Boost_LIBRARIES})
message("FOOBAR_INCLUDE_DIRS: " ${FOO_INCLUDE_DIRS})
message("FOOBAR_LIBRARIES: " ${FOO_LIBRARIES})


add_executable(BarExe bar.cxx)
target_link_libraries(BarExe foo)
target_link_libraries(BarExe vtkbool)
#target_link_libraries(BarExe VTK)
target_link_libraries(BarExe Boost::filesystem)







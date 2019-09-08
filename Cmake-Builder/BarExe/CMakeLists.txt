cmake_minimum_required(VERSION 2.8)
project(BarEXE)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
add_definitions(-DvtkSlicerRtCommon_EXPORTS)
add_definitions(-DVTK_EXPORT)

find_package(VTK REQUIRED)
find_package(Boost REQUIRED COMPONENTS filesystem) #remove to run Cmake for sln
find_package(VTKBool REQUIRED)
find_package(foo REQUIRED)
find_package(sqlite3 REQUIRED)
#find_package(sqlite3 CONFIG REQUIRED)

#include_directories(${PROJECT_SOURCE_DIR})
#include_directories(${PROJECT_SOURCE_DIR}/include/sdc3dlibs)


include_directories(${VTK_USE_FILE})
include_directories(${VTKBOOL_INCLUDE_DIRS})
include_directories(${Boost_INCLUDE_DIRS})
include_directories(${VTKBOOL_INCLUDE_DIRS}/libs/vp)
include_directories(${FOO_INCLUDE_DIRS})
include_directories(${SQLite3_INCLUDE_DIRS})


message("----------------")
message("VTK_USE_FILE: " ${VTK_USE_FILE})
message("VTK_LIBRARIES: " ${VTK_LIBRARIES})
message("VTKBOOL_INCLUDE_DIRS: " ${VTKBOOL_INCLUDE_DIRS})
message("VTKBOOL_LIBRARIES: " ${VTKBOOL_LIBRARIES}) 
message("VTKBOOL_LIBRARIES_DIR: " ${VTKBOOL_LIBRARIES_DIR}) 
message("Boost_INCLUDE_DIRS: " ${Boost_INCLUDE_DIRS})
message("Boost_LIBRARIES: " ${Boost_LIBRARIES})
message("FOO_INCLUDE_DIRS: " ${FOO_INCLUDE_DIRS})
message("FOO_LIBRARIES: " ${FOO_LIBRARIES})
message("FOO_LIBRARIES_DIR: " ${FOO_LIBRARIES_DIR})
message("SQLite3_INCLUDE_DIRS: " ${SQLite3_INCLUDE_DIRS})
message("SQLite3_LIBRARIES: " ${SQLite3_LIBRARIES})

#SET(CMAKE_FIND_LIBRARY_SUFFIXES ".lib")
#SET(BUILD_SHARED_LIBS OFF)
#SET(CMAKE_EXE_LINKER_FLAGS "-static")
add_executable(BarExe bar.cxx)

target_link_libraries(BarExe ${FOO_LIBRARIES_DIR})
target_link_libraries(BarExe vtkbool)
target_link_libraries(BarExe ${VTK_LIBRARIES})
target_link_libraries(BarExe Boost::filesystem)
target_link_libraries(BarExe ${SQLite3_LIBRARIES})

file(COPY ${FOO_LIBRARIES_DIR}/${FOO_LIBRARIES}.dll DESTINATION ${PROJECT_BINARY_DIR})
file(COPY ${VTKBOOL_LIBRARIES_DIR}/${VTKBOOL_LIBRARIES}.dll DESTINATION ${PROJECT_BINARY_DIR})

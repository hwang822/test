
#include <vtkCubeSource.h>
#include "vtkPolyDataBooleanFilter.h"
//#include "foo.h"

int main(int argc, char* argv[])
{
	//foo();	
	vtkCubeSource* cu = vtkCubeSource::New();
	cu->SetYLength(.5);

	vtkPolyDataBooleanFilter* bf = vtkPolyDataBooleanFilter::New();
	bf->SetInputConnection(0, cu->GetOutputPort());

	return 0;
}

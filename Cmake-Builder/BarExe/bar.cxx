
#include <vtkCubeSource.h>
#include "foo.h"

int main(int argc, char* argv[])
{
	foo();	
	vtkCubeSource* cu = vtkCubeSource::New();
	cu->SetYLength(.5);

	return 0;
}

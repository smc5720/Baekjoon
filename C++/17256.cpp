#include <iostream>
using namespace std;

// https://www.acmicpc.net/problem/17256

int main()
{
	int ax, ay, az;
	int bx, by, bz;
	int cx, cy, cz;

	cin >> ax >> ay >> az;
	cin >> cx >> cy >> cz;

	bx = cx - az;
	by = cy / ay;
	bz = cz - ax;

	cout << bx << " " << by << " " << bz << endl;

	return 0;
}
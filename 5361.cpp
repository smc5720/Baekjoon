#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/5361

int main()
{
	int times, num;

	double rifle, visual, auditory, arm, leg;

	rifle = 350.34;
	visual = 230.90;
	auditory = 190.55;
	arm = 125.30;
	leg = 180.90;

	cin >> times;

	for (int i = 0; i < times; i++)
	{
		double A, B, C, D, E;

		double result;

		result = 0;

		cin >> A >> B >> C >> D >> E;

		result += rifle * A;
		result += visual * B;
		result += auditory * C;
		result += arm * D;
		result += leg * E;

		printf("$%.2f\n", result);
	}

	return 0;
}
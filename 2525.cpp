#include <iostream>
using namespace std;

// https://www.acmicpc.net/problem/2525

int main()
{
	int hour, min, input;

	cin >> hour >> min >> input;

	min += input;

	hour += min / 60;

	if (hour > 23)
	{
		hour -= 24;
	}

	min %= 60;

	cout << hour << " " << min << endl;

	return 0;
}
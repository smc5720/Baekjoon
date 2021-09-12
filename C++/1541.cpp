#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>
#include <set>

using namespace std;

// https://www.acmicpc.net/problem/1541

string str;

int minResult(void)
{
	int result = 0;
	string temp = "";
	bool minus = false;

	for (int i = 0; i <= str.size(); i++)
	{
		if (str[i] == '+' || str[i] == '-' || str[i] == '\0')
		{
			if (minus)
			{
				result -= stoi(temp);
			}
			else
			{
				result += stoi(temp);
			}

			temp = "";

			if (str[i] == '-')
			{
				minus = true;
			}

			continue;
		}

		temp += str[i];
	}

	return result;
}

int main(void)
{
	cin >> str;

	cout << minResult() << endl;

	return 0;
}
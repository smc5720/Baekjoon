#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>

using namespace std;

// https://www.acmicpc.net/problem/9047

int T;

int returnGreat(string num)
{
	vector <int> v;

	for (int i = 0; i < 4; i++)
	{
		v.push_back(num[i] - 48);
	}

	sort(v.begin(), v.end());

	int n;
	n = 1000 * v[3] + 100 * v[2] + 10 * v[1] + 1 * v[0];

	return n;
}

int returnLess(string num)
{
	vector <int> v;

	for (int i = 0; i < 4; i++)
	{
		v.push_back(num[i] - 48);
	}

	sort(v.begin(), v.end());

	int n;
	n = 1000 * v[0] + 100 * v[1] + 10 * v[2] + 1 * v[3];

	return n;
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> T;

	for (int i = 0; i < T; i++)
	{
		string num;
		cin >> num;

		int cnt;
		cnt = 0;

		if (num == "6174")
		{
			cout << cnt << "\n";
			continue;
		}

		int result;
		result = returnGreat(num) - returnLess(num);
		cnt += 1;

		while (result != 6174)
		{
			cnt += 1;
			num = to_string(result);

			if (num.length() == 1)
			{
				num = "000" + num;
			}

			else if (num.length() == 2)
			{
				num = "00" + num;
			}

			else if (num.length() == 3)
			{
				num = "0" + num;
			}

			result = returnGreat(num) - returnLess(num);
		}

		cout << cnt << "\n";
	}

	return 0;
}
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

// https://www.acmicpc.net/problem/2407

int N, M;
string cache[101][101];

string bigNumAdd(string A, string B)
{
	string result;
	int sum;
	sum = 0;

	while (!A.empty() || !B.empty() || sum > 0)
	{
		if (!A.empty())
		{
			sum += A.back() - '0';
			A.pop_back();
		}

		if (!B.empty())
		{
			sum += B.back() - '0';
			B.pop_back();
		}

		result.push_back((sum % 10) + '0');
		sum /= 10;
	}

	reverse(result.begin(), result.end());
	return result;
}

string combination(int n, int r)
{
	if (n == r || r == 0)
	{
		return "1";
	}

	string &result = cache[n][r];

	if (result != "")
	{
		return result;
	}

	result = bigNumAdd(combination(n - 1, r - 1), combination(n - 1, r));
	return result;
}

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;
	cout << combination(N, M) << "\n";

	return 0;
}
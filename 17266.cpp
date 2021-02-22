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

// https://www.acmicpc.net/problem/17266

int N, M;
int arr[100000];

bool checkHeight(int h)
{
	int start;
	start = 0;

	for (int i = 0; i < M; i++)
	{
		if (arr[i] - h > start)
		{
			return false;
		}

		start = arr[i] + h;
	}

	if (start < N)
	{
		return false;
	}

	return true;
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;

	for (int i = 0; i < M; i++)
	{
		cin >> arr[i];
	}

	int leftPt, rightPt;
	leftPt = 1;
	rightPt = N;
	
	int ans;
	ans = 0;

	while (leftPt <= rightPt)
	{
		int mid;
		mid = (leftPt + rightPt) / 2;

		if (checkHeight(mid))
		{
			ans = mid;
			rightPt = mid - 1;
		}

		else
		{
			leftPt = mid + 1;
		}
	}

	cout << ans << "\n";

	return 0;
}
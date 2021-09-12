#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/6236

long long N, M;
long long leftPt, rightPt;
int arr[100000];

long long getK(int num)
{
	int cnt, sum;
	cnt = 0;
	sum = 0;

	for (int i = 0; i < N; i++)
	{
		if (sum < arr[i])
		{
			sum = num;
			cnt += 1;
		}

		if (num < arr[i])
		{
			cnt = 1000000000;
			break;
		}

		sum -= arr[i];
	}

	return cnt;
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;

	for (int i = 0; i < N; i++)
	{
		cin >> arr[i];
	}

	if (N > 0)
	{
		leftPt = 0;
		rightPt = 1000000000;
	}

	long long ans;
	ans = 1000000000;

	while (leftPt <= rightPt)
	{
		long long mid;
		mid = (leftPt + rightPt) / 2;

		// mid�� Ŀ���� K�� ���� �۾�����.
		int K;
		K = getK(mid);

		// K�� ���� M���� ũ��. �� mid�� Ű���� �Ѵ�.
		if (K > M)
		{
			leftPt = mid + 1;
		}

		// K�� ���� M���� �۴�. �� mid�� �ٿ��� �Ѵ�.
		else
		{
			ans = mid;
			rightPt = mid - 1;
		}
	}

	cout << ans << "\n";

	return 0;
}
#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/4641

int arr[15];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int n;
	cin >> n;

	while (n != -1)
	{
		arr[0] = n;
		cin >> n;

		int idx;
		idx = 1;

		int ans;
		ans = 0;

		while (n != 0)
		{
			arr[idx] = n;
			cin >> n;
			idx += 1;
		}

		for (int i = 0; i < idx; i++)
		{
			for (int j = 0; j < idx; j++)
			{
				if (i == j)
				{
					continue;
				}

				if (arr[i] * 2 == arr[j])
				{
					ans += 1;
				}
			}
		}

		cout << ans << "\n";

		cin >> n;
	}

	return 0;
}
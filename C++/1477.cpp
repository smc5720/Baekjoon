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

int tb1[100001];

// https://www.acmicpc.net/problem/1477

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

    int n, m, l;
    cin >> n >> m >> l;

    for (int i = 1; i <= n; i++)
    {
        cin >> tb1[i];
    }

    tb1[0] = 0;
    tb1[n + 1] = l;
    sort(tb1, tb1 + n + 2);

    int left = 1;
    int right = l - 1;

    while (left <= right)
    {
        int mcnt = 0;
        int mid = left + (right - left) / 2;

        for (int i = 1; i <= n + 1; ++i)
        {
            if (tb1[i] - tb1[i - 1] > mid)
            {
                mcnt += ((tb1[i] - tb1[i - 1] - 1)) / mid;
            }
        }

        if (mcnt > m)
        {
            left = mid + 1;
        }

        else
        {
            right = mid - 1;
        }
    }

    cout << left << endl;

	return 0;
}
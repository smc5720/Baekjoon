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

// https://www.acmicpc.net/problem/1074

int N, r, c;
int ans;

void funcZ(int x, int y, int n)
{
    if (x == r && y == c)
    {
        cout << ans << "\n";
        return;
    }


    if (r < x + n && r >= x && c < y + n && c >= y)
    {
        funcZ(x, y, n / 2);
        funcZ(x, y + n / 2, n / 2);
        funcZ(x + n / 2, y, n / 2);
        funcZ(x + n / 2, y + n / 2, n / 2);
    }

    else
    {
        ans += n * n;
    }
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	ans = 0;

	cin >> N >> r >> c;

	funcZ(0, 0, (1 << N));

	return 0;
}
class Solution {
public:
     vector<vector<pair<int,int>>> adj;
     int n;
     void dijkstra(int src,vector<int>& d)
     {
        d[src]=0;
        set<pair<int,int>> q;
        q.insert({0,src});
        while(!q.empty())
        {
            int node=q.begin()->second;
            q.erase(q.begin());
            for(auto& x:adj[node])
            {
                int to=x.first;
                int len=x.second;
                if(d[node]+len<d[to])
                {
                    q.erase({d[to],to});
                    d[to]=d[node]+len;
                    q.insert({d[to],to});
                }
            }
        }
     }
    vector<bool> findAnswer(int n, vector<vector<int>>& a) {

         this->n=n;
         this->adj.resize(n);
         for(auto& x:a)
         {
            adj[x[0]].push_back({x[1],x[2]});
            adj[x[1]].push_back({x[0],x[2]});
         }
         vector<int> d1(n,INT_MAX),d2(n,INT_MAX);
         dijkstra(0,d1);
         dijkstra(n-1,d2);
         int m=a.size();
         vector<bool> ans(m,false);
         for(int i=0;i<m;i++)
         {
            int u=a[i][0];
            int v=a[i][1];
            int cost=a[i][2];
            if(d1[u]!=INT_MAX && d2[v]!=INT_MAX &&
             (d1[u]+cost+d2[v]==d1[n-1] || d2[u]+cost+d1[v]==d1[n-1] ))
             ans[i]=true;
         }
         return ans;
    }
};
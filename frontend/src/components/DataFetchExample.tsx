import { useEffect, useState } from "react";
import { api } from "../../api/client";

// API로 데이터를 가져오는 예시 컴포넌트
export function DataFetchExample() {
  const [data, setData] = useState<any>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const response = await api.getHomeData();
        setData(response.data);
      } catch (err: any) {
        setError(err.message || "데이터를 불러오는데 실패했습니다.");
        console.error("API Error:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) return <div>로딩 중...</div>;
  if (error) return <div>에러: {error}</div>;

  return (
    <div>
      <h2>API 데이터:</h2>
      <pre>{JSON.stringify(data, null, 2)}</pre>
    </div>
  );
}

// 사용법 예시
// import { DataFetchExample } from './components/DataFetchExample';
// <DataFetchExample />

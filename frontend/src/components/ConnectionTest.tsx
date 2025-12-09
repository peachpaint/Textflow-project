import { useEffect, useState } from "react";
import axios from "axios";

export function ConnectionTest() {
  const [status, setStatus] = useState<"testing" | "success" | "error">(
    "testing"
  );
  const [data, setData] = useState<any>(null);
  const [error, setError] = useState<string>("");

  useEffect(() => {
    testConnection();
  }, []);

  const testConnection = async () => {
    try {
      setStatus("testing");

      // 방법 1: 프록시 사용
      const response = await axios.get("/api/home");

      // 방법 2: 직접 URL 사용 (프록시 없이)
      // const response = await axios.get('http://localhost:8080/Textflow/home');

      setData(response.data);
      setStatus("success");
      console.log("✅ Backend 연결 성공:", response.data);
    } catch (err: any) {
      setStatus("error");
      setError(err.message);
      console.error("❌ Backend 연결 실패:", err);
    }
  };

  return (
    <div className="fixed bottom-4 right-4 p-4 bg-white rounded-lg shadow-lg max-w-md z-50">
      <h3 className="text-lg font-bold mb-2">🔌 Backend 연결 테스트</h3>

      {status === "testing" && (
        <div className="flex items-center gap-2 text-blue-600">
          <div className="w-4 h-4 border-2 border-blue-600 border-t-transparent rounded-full animate-spin"></div>
          <span>연결 확인 중...</span>
        </div>
      )}

      {status === "success" && (
        <div className="text-green-600">
          <p className="font-semibold">✅ 연결 성공!</p>
          <div className="mt-2 p-2 bg-gray-50 rounded text-xs overflow-auto max-h-32">
            <pre>{JSON.stringify(data, null, 2)}</pre>
          </div>
        </div>
      )}

      {status === "error" && (
        <div className="text-red-600">
          <p className="font-semibold">❌ 연결 실패</p>
          <p className="text-sm mt-1">{error}</p>
          <button
            onClick={testConnection}
            className="mt-2 px-3 py-1 bg-blue-500 text-white rounded text-sm hover:bg-blue-600"
          >
            다시 시도
          </button>
        </div>
      )}

      <div className="mt-3 text-xs text-gray-500">
        <p>Backend: http://localhost:8080/Textflow</p>
        <p>Endpoint: GET /home</p>
      </div>
    </div>
  );
}

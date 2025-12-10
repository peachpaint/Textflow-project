import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import WorkDetail from "./pages/WorkDetail";

export default function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/work/:workId" element={<WorkDetail />} />
        {/* TODO: 추가 라우트 */}
        {/* <Route path="/viewer/:workId/:episodeId" element={<Viewer />} /> */}
        {/* <Route path="/search" element={<Search />} /> */}
        {/* <Route path="/my" element={<MyPage />} /> */}
      </Routes>
    </Router>
  );
}

// Import styles of packages that you've installed.
// All packages except `@mantine/hooks` require styles imports
import "@mantine/core/styles.css";

import { MantineProvider } from "@mantine/core";
import { HashRouter, Route, Routes } from "react-router-dom";
import { RootPage } from "./pages/page";
import TestsPage from "./pages/tests/page";
import TestsSummaryPage from "./pages/tests/TestsSummaryPage";
import { AppLayout } from "./_components/AppShell";
import TestsCoveragePage from "./pages/tests/TestsCoveragePage";
import ArchitecturePage from "./pages/architecture/page";
import UMLDiagramsPage from "./pages/architecture/UMLDiagramsPage";
import { ModalsProvider } from "@mantine/modals";
import ManualTestsPage from "./pages/tests/ManualTestsPage";

export default function App() {
  return (
    <>
      <MantineProvider>
        <ModalsProvider>
          <HashRouter>
            <Routes>
              <Route path="/" element={<AppLayout />}>
                <Route path="" element={<RootPage />} />
                <Route path="tests">
                  <Route index element={<TestsPage />} />
                  <Route path="summary" element={<TestsSummaryPage />} />
                  <Route path="coverage" element={<TestsCoveragePage />} />
                  <Route path="manual" element={<ManualTestsPage />} />
                </Route>
                <Route path="architecture">
                  <Route index element={<ArchitecturePage />} />
                  <Route path="uml" element={<UMLDiagramsPage />} />
                </Route>
                <Route path="weeks"></Route>
              </Route>
            </Routes>
          </HashRouter>
        </ModalsProvider>
      </MantineProvider>
    </>
  );
}

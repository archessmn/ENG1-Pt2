import { Center, Group, Stack, Title } from "@mantine/core";
import { NavLink } from "react-router-dom";

export default function TestsPage() {
  return (
    <>
      <Center>
        <Title>Test Reports</Title>
      </Center>
      <Center>
        <Group>
          <Stack>
            <NavLink to={"/tests/summary"}>Tests Summary</NavLink>
          </Stack>
          <Stack>
            <NavLink to={"/tests/coverage"}>Tests Coverage</NavLink>
          </Stack>
        </Group>
      </Center>
    </>
  );
}

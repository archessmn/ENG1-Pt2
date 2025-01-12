import { Center, Group, Stack, Title } from "@mantine/core";
import { NavLink } from "react-router-dom";

export default function ArchitecturePage() {
  return (
    <>
      <Center>
        <Title>Architecture</Title>
      </Center>
      <Center>
        <Group>
          <Stack>
            <NavLink to={"/architecture/uml"}>UML Diagram</NavLink>
          </Stack>
          {/* <Stack>
            <NavLink to={"/tests/coverage"}>Tests Coverage</NavLink>
          </Stack> */}
        </Group>
      </Center>
    </>
  );
}

import { Center, Text, Title } from "@mantine/core";
import CustomImage from "../../_components/CustomImage";

export default function UMLDiagramsPage() {
  return (
    <>
      <Center>
        <Title>UML Diagram</Title>
      </Center>
      <Text>
        Click to zoom. You can find a link to download the image by enlarging
        it.
      </Text>
      <CustomImage src="/final-uml-diagram.png" />;
    </>
  );
}

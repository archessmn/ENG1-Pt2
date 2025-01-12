import {
  PolymorphicComponentProps,
  TextProps,
  Text as MantineText,
} from "@mantine/core";

export default function Text(props: PolymorphicComponentProps<"p", TextProps>) {
  return <MantineText style={{ fontSize: 20 }} {...props} />;
}

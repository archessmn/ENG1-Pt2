import {
  Avatar,
  Box,
  Button,
  Center,
  Group,
  // NavLink,
  Space,
  Stack,
  Text,
  Title,
} from "@mantine/core";
// import { NavLink as ReactNavLink } from "react-router-dom";
import { Link } from "react-router-dom";
import { FaArrowRight, FaGithub } from "react-icons/fa";
import { IoDocumentOutline } from "react-icons/io5";
import { PiArrowBendDownRight } from "react-icons/pi";

export function RootPage() {
  const imageLinks = {
    req: "https://static.wixstatic.com/media/a3c153_d5f554ea6f054cffb95517d26fe14889~mv2.jpg/v1/crop/x_0,y_100,w_2652,h_2652/fill/w_121,h_121,al_c,q_80,usm_0.66_1.00_0.01,enc_avif,quality_auto/sebastien-noel-i0tLUaCA8Ec-unsplash-vertical_02.jpg",
    arch: "https://static.wixstatic.com/media/a3c153_c32e66b082614a0bb8072e32e0de9ce2~mv2.png/v1/crop/x_387,y_0,w_650,h_651/fill/w_121,h_121,al_c,q_85,usm_0.66_1.00_0.01,enc_avif,quality_auto/shutterstock_1527855629%2520copy_edited_edited.png",
    meth: "https://static.wixstatic.com/media/65b018bc7a184dd4bda27809bc2ce0bf.jpg/v1/crop/x_97,y_0,w_1806,h_1806/fill/w_121,h_121,al_c,q_80,usm_0.66_1.00_0.01,enc_avif,quality_auto/Urban%20Planning.jpg",
    risk: "https://static.wixstatic.com/media/11062b_51b43d659ec24e46bc510178f0e30101~mv2.jpg/v1/crop/x_512,y_0,w_3160,h_3160/fill/w_121,h_121,al_c,q_80,usm_0.66_1.00_0.01,enc_avif,quality_auto/Safety%20Wear.jpg",
    impl: "https://static.wixstatic.com/media/be0c8732c52445f79601e0965fa55b0e.jpg/v1/crop/x_125,y_0,w_750,h_750/fill/w_121,h_121,al_c,q_80,usm_0.66_1.00_0.01,enc_avif,quality_auto/Engineering%20Sketch.jpg",
    jar: "https://static.wixstatic.com/media/53bd28_e079e2f7d938470cb159acddb847a9f9~mv2.png/v1/crop/x_200,y_0,w_500,h_500/fill/w_121,h_121,al_c,q_85,usm_0.66_1.00_0.01,enc_avif,quality_auto/duke.png",
  };

  return (
    <>
      <Center>
        <Title
          style={{
            fontSize: "151px",
          }}
          mt={"20vh"}
        >
          OCTAGAME
        </Title>
      </Center>
      <Center>
        <Title
          c={"#5731dc"}
          size={71}
          mt={"20vh"}
          style={{
            textDecoration: "underline dotted",
          }}
        >
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;UniSim&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </Title>
      </Center>
      <Center>
        <Group>
          <IoDocumentOutline size={150} color="#4854c7" />
          <Title size={56}>Our Documents</Title>
          <IoDocumentOutline size={150} color="#4854c7" />
        </Group>
      </Center>
      <Center>
        <Text>
          We have plenty of documents for you to have a gander at. So why don't
          you have a look
        </Text>
      </Center>
      <Space h={80} />
      <Center>
        <Group>
          <Stack>
            <Center>
              <Avatar src={imageLinks.req} size={121} />
            </Center>
            <Center>
              <Text style={{ fontSize: 20 }}>Requirements</Text>
            </Center>
            <Center>
              <Link
                to={"https://github.com/archessmn"}
                target="_blank"
                rel="noopener noreferrer"
              >
                <Text>Req2</Text>
              </Link>
            </Center>
            <Space h={50} />
            <Center>
              <Avatar src={imageLinks.risk} size={121} />
            </Center>
            <Center>
              <Text style={{ fontSize: 20 }}>Risk Assessment</Text>
            </Center>
            <Center>
              <Link
                to={"https://github.com/archessmn"}
                target="_blank"
                rel="noopener noreferrer"
              >
                <Text>Risk2</Text>
              </Link>
            </Center>
          </Stack>
          <Stack w={500}>
            <Stack>
              <Center>
                <Avatar src={imageLinks.arch} size={121} />
              </Center>
              <Center>
                <Text>Architecture</Text>
              </Center>
              <Center>
                <Link
                  to={"https://github.com/archessmn"}
                  target="_blank"
                  rel="noopener noreferrer"
                >
                  <Text>Arch2</Text>
                </Link>
              </Center>
            </Stack>
          </Stack>
          <Stack>
            <Stack>
              <Center>
                <Avatar src={imageLinks.meth} size={121} />
              </Center>
              <Center>
                <Text>Methods & Planning</Text>
              </Center>
              <Center>
                <Link
                  to={"https://github.com/archessmn"}
                  target="_blank"
                  rel="noopener noreferrer"
                >
                  <Text>Plan2</Text>
                </Link>
              </Center>
            </Stack>
            <Space h={50} />
            <Stack>
              <Center>
                <Avatar src={imageLinks.impl} size={121} />
              </Center>
              <Center>
                <Text>Implementation</Text>
              </Center>
              <Center>
                <Link
                  to={"https://github.com/archessmn"}
                  target="_blank"
                  rel="noopener noreferrer"
                >
                  <Text>Impl2</Text>
                </Link>
              </Center>
            </Stack>
          </Stack>
        </Group>
      </Center>
      <Space h={50} />
      <Center>
        <Group>
          <Stack w={500}>
            <Stack>
              <Center>
                <Avatar src={imageLinks.jar} size={121} />
              </Center>
              <Center>
                <a
                  href={"/assets/jar/UniSim2-1.0.0.jar"}
                  target="_blank"
                  rel="noopener noreferrer"
                >
                  <Text>Here is the JAR executable</Text>
                </a>
              </Center>
            </Stack>
          </Stack>
          <Stack>
            <Center>
              <Button
                component={Link}
                rightSection={<FaArrowRight size={30} />}
                size="40"
                h={80}
                bg={"#3f7652"}
                fw={100}
                radius={40}
                to={
                  "https://drive.google.com/drive/folders/1_c1pkNcemokb3aUFgCjt7k40SX_Fa1QT?usp=sharing"
                }
              >
                Files
              </Button>
            </Center>
          </Stack>
        </Group>
      </Center>
      <Space h={40} />
      <Box
        style={{
          width: "100%",
          height: "426px",
          backgroundColor: "#edebeb",
        }}
      >
        <Center>
          <Group>
            <Stack>
              <Center>
                <FaGithub size={195} />
              </Center>
              <Space h={50} />
              <Center>
                <PiArrowBendDownRight size={139} />
              </Center>
            </Stack>
            <Stack w={500}>
              <Center>
                <Title size={56}>GitHub Repository</Title>
              </Center>
              <Space h={80} />
              <Center>
                <Link
                  to={"https://github.com/archessmn/Eng1-Pt2"}
                  target="_blank"
                  rel="noopener noreferrer"
                >
                  <Text style={{ fontSize: "41px" }} pt={50}>
                    Link to repository
                  </Text>
                </Link>
              </Center>
            </Stack>
            <Stack>
              <Center>
                <FaGithub size={195} />
              </Center>
              <Space h={50} />
              <Center>
                <PiArrowBendDownRight
                  size={139}
                  style={{
                    transform: "scale(-1, 1)",
                    transformOrigin: "center",
                  }}
                />
              </Center>
            </Stack>
          </Group>
        </Center>
      </Box>
    </>
  );
}

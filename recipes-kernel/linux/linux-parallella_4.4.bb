
LINUX_VERSION = "4.4"
KBRANCH = "parallella-linux-2016.3"
# pin after 'parallella-linux-esdk-2016.3.1' tag
SRCREV = "eb7c3a109b494e7f27387d119882b7a127759ada"

include linux-parallella.inc

# add the drivers necessary with SRCREV eb7c3a1
SRC_URI_append = " \
	file://0001-dma-proof-concept-pl330.patch \
	file://0002-dmaengine-commit-6906086.patch \
	file://0003-dmaengine-commit-0c400db.patch \
	file://0004-dma-commit-23ef060.patch \
	file://0005-dma-proof-concept-xilinx-dma.patch \
	file://0006-drm-proof-concept-adi_axi_hdmi.patch \
	file://0007-clk-axi-clkgen-proof-concept.patch \
	file://0008-Sound-SPDIF-DAI-commit-80336ab.patch \
	file://0009-ASoC-commit-7e44b46.patch \
	file://0010-Sound-proof-concept-add-adv7511_hdmi.patch \
	"

# add the device tree patches necessary with SRCREV eb7c3a1
SRC_URI_append = " \
	file://0012-HDMI-device-tree.patch \
	"



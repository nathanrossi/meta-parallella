

KBRANCH = "parallella-linux-2016.3"
LINUX_VERSION = "4.4"
SRCREV = "eb7c3a109b494e7f27387d119882b7a127759ada"

include linux-parallella.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-parallella/4.4:"

SRC_URI += " \
	file://0001-dma-proof-concept-pl330.patch \
	file://0002-dmaengine-commit-6906086.patch \
	file://0003-dmaengine-commit-0c400db.patch \
	file://0004-dma-commit-23ef060.patch \
	file://0005-dma-proof-concept-xilinx-dma.patch \
	"


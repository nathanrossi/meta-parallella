DECRIPTION = "Parallella Kernel (based on linux-xlnx/adi vendor trees)"

LINUX_VERSION_EXTENSION ?= "-parallella"

KBRANCH = "main"
LINUX_VERSION = "3.14.12"
SRCREV = "f8baec676b762fbb9c5fe5d50c9ef89da88f36a9"

include recipes-kernel/linux/linux-xlnx.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/config:"
SRC_URI = " \
		git://github.com/parallella/parallella-linux.git;protocol=https;branch=${KBRANCH} \
		file://xilinx-base;type=kmeta;destsuffix=xilinx-base \
		file://parallella-base;type=kmeta;destsuffix=parallella-base \
		"

COMPATIBLE_MACHINE = "parallella"

KERNEL_FEATURES += "features/epiphany/epiphany.scc"


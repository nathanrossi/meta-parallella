
DESCRIPTION = "Analog Devices Inc Kernel (with Parallella modifications)"

# This version extension should match CONFIG_LOCALVERSION in defconfig
LINUX_VERSION_EXTENSION ?= "-parallella"

include recipes-kernel/linux/linux-xlnx.inc

# Sources
FILESEXTRAPATHS_prepend := "${THISDIR}/config:"
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-analogdevicesinc/3.19:"

# Now replace the SRC_URI

KBRANCH = "2015_R1"
LINUX_VERSION = "3.19"
SRCREV = "62b9dd8ce075c8b3c041b5ea0c806f02c9e97261"

SRC_URI = " git://github.com/analogdevicesinc/linux.git;branch=${KBRANCH} \
		file://xilinx-base;type=kmeta;destsuffix=xilinx-base \
		file://parallella-base;type=kmeta;destsuffix=parallella-base \
		file://add-epiphany.patch \
		file://add-parallella_defconfig.patch \
		"

COMPATIBLE_MACHINE = "parallella"

KERNEL_FEATURES += "features/epiphany/epiphany.scc"

# Add the parallella machine specific configuration
require linux-parallella-machines.inc

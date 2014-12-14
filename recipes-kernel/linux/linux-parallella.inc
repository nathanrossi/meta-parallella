
DESCRIPTION = "Parallella Kernel (based on linux-xlnx/adi vendor trees)"

# This version extension should match CONFIG_LOCALVERSION in defconfig
LINUX_VERSION_EXTENSION ?= "-parallella"

include recipes-kernel/linux/linux-xlnx.inc

# Sources
FILESEXTRAPATHS_prepend := "${THISDIR}/config:"

# Now replace the SRC_URI Note have to re-apply linux-xilinx-configs.inc additions

KBRANCH ?= "main"
SRC_URI = " \
		git://github.com/parallella/parallella-linux.git;protocol=https;branch=${KBRANCH} \
		file://xilinx-base;type=kmeta;destsuffix=xilinx-base \
		file://parallella-base;type=kmeta;destsuffix=parallella-base \
		"

COMPATIBLE_MACHINE = "parallella"

KERNEL_FEATURES += "features/epiphany/epiphany.scc"

# Add the parallella machine specific configuration
require linux-parallella-machines.inc

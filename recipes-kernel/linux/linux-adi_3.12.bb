DECRIPTION = "ADI Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

# This version extension should match CONFIG_LOCALVERSION in defconfig
LINUX_VERSION_EXTENSION ?= "-adi"
PV = "${LINUX_VERSION}${LINUX_VERSION_EXTENSION}+git${SRCREV}"

# Source Directory
S = "${WORKDIR}/git"

# Inherit/include base functionality
inherit kernel
require recipes-kernel/linux/linux-machine-common.inc

# Override COMPATIBLE_MACHINE to include your machine in a bbappend file.
COMPATIBLE_MACHINE = "qemuzynq|zynq"

PR = "r1"

KBRANCH = "bcxcube_epiphany_driver"
LINUX_VERSION = "3.12"
SRCREV = "203d6a659b5b74fbf279a83a2121ef604769abe7"

SRC_URI = "git://github.com/parallella/parallella-linux-adi.git;protocol=https;branch=${KBRANCH}"

do_configure_prepend() {
	cp ${S}/arch/arm/configs/parallella_defconfig ${B}/.config

	# Enable the Epiphany drive
	echo "CONFIG_EPIPHANY=y" >> ${B}/.config
	echo "" >> ${B}/.config

}


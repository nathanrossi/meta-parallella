SUMMARY = "Parallella Kernel"

# This version extension should match CONFIG_LOCALVERSION in defconfig
LINUX_VERSION_EXTENSION ?= "-parallella"
PV = "${LINUX_VERSION}${LINUX_VERSION_EXTENSION}+git${SRCPV}"

KBRANCH ?= "parallella-linux-2016.3"

# Default linux-parallella source
SRC_URI = "git://github.com/parallella/parallella-linux.git;protocol=https;branch=${KBRANCH}"

# add config fragments
FILESEXTRAPATHS_prepend := "${THISDIR}/config:"
SRC_URI_append = " \
		file://parallella/parallella.cfg \
		file://parallella/epiphany.cfg \
		file://parallella/extra.cfg \
		"

require recipes-kernel/linux/linux-yocto.inc

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE_parallella = "parallella"
COMPATIBLE_MACHINE_parallella-hdmi = "parallella-hdmi"

# defconfig is based on multi_v7
KBUILD_DEFCONFIG = "multi_v7_defconfig"

do_configure_prepend() {
	cat ${S}/arch/${ARCH}/configs/${KBUILD_DEFCONFIG} > ${B}/.config

	for i in ${WORKDIR}/parallella/*.cfg; do
		cat $i >> ${B}/.config
	done

	yes '' | oe_runmake oldconfig
}


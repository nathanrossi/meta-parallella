require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "dtc-native"

SRCREV = "4141e85bcd79c0b9b16def710e527f165107b7af"

PV = "v2016.next+parallella+git${SRCPV}"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append_parallella-common = " \
		file://zynq-Add-Adapteva-Parallella-board-support.patch \
		file://ARM-zynq-Add-ps7_init_gpl.c-h-for-Parallella.patch \
		file://HACK-ARM-zynq-Force-MMC-boot-regardless-of-boot-conf.patch \
		"


SUMMARY = "Parallella hdmi Bitstream"
SECTION = "bsp"

LICENSE = "GPLv3"
#LIC_FILES_CHKSUM = "file://COPYING;md5=3c34afdc3adf82d2448f12715a255122"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

#SRC_URI = "git://github.com/parallella/parallella-hw.git;protocol=https"
#SRCREV = "ce97134bc01e8f3b8374e0e74b2ca191c8873f59"

SRC_URI = "git://github.com/peteasa/examples.git;protocol=https;branch=elink-redesign"
SRCREV = "4f79bc913f6e254441457409a47a487b17af30cd"

S = "${WORKDIR}/git"

INHIBIT_DEFAULT_DEPS = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "parallella-hdmi"

inherit deploy

do_compile() {
	:
}

do_install() {
	:
}

do_deploy() {
	install -d ${DEPLOY_DIR_IMAGE}/bitstreams
	for i in $(ls ${S}/fpga/bitstreams/ | grep parallella_.*_hdmi.*\.bit\.bin); do
		install ${S}/fpga/bitstreams/$i ${DEPLOY_DIR_IMAGE}/bitstreams
	done
}
addtask deploy before do_build after do_install


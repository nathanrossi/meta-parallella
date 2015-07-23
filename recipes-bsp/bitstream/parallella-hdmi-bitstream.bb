SUMMARY = "Parallella hdmi Bitstream"
SECTION = "bsp"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=3c34afdc3adf82d2448f12715a255122"

SRC_URI = "git://github.com/parallella/parallella-hw.git;branch=${SBRANCH};protocol=https"

SBRANCH = "2015.1"
SRCREV = "beb4ca09e9616cc76364735f19e8502c438cd61c"

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
	# for now use the official headless bitstream.. later override this with hdmi version official or home grown!
	for i in $(ls ${S}/fpga/bitstreams/ | grep parallella_.*\.bit\.bin); do
		install ${S}/fpga/bitstreams/$i ${DEPLOY_DIR_IMAGE}/bitstreams
	done
}
addtask deploy before do_build after do_install


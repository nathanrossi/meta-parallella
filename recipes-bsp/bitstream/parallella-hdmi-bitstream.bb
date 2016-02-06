SUMMARY = "Unofficial Parallella hdmi Bitstream"
SECTION = "bsp"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRC_URI = "git://github.com/parallella/parallella-hw.git;branch=${SBRANCH};protocol=https"

## 7020_hdmi and 7010_hdmi oh fpga with hdmi
EXAMPLEBR = "parallella-oh"
SRC_URI = "git://github.com/peteasa/examples.git;branch=${EXAMPLEBR}"
SRCREV = "49d95db0597d6e1d21c5a2e0b047e83e14ffd0bb"

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


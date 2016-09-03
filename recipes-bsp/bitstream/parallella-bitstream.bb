SUMMARY = "Parallella Headless Bitstream"
SECTION = "bsp"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=17e9aa6b5e59214b6fdb51d372e23ee8"

SRC_URI = "git://github.com/parallella/pubuntu.git"
SRCREV = "d5785963c3587fcbac9557129f31a5ac496b266d"

S = "${WORKDIR}/git"

INHIBIT_DEFAULT_DEPS = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "parallella"

inherit deploy

do_compile() {
	:
}

do_install() {
	:
}

do_deploy() {
	install -d ${DEPLOYDIR}/bitstreams
	for i in $(ls ${S}/fpga_bitfiles/ | grep parallella_.*_headless.*\.bit\.bin); do
		install ${S}/fpga_bitfiles/$i ${DEPLOYDIR}/bitstreams
	done
}
addtask deploy before do_build after do_install


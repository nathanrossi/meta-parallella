SUMMARY = "Unofficial Parallella hdmi Bitstream"
SECTION = "bsp"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"

##No official bitsteam with hdmi just yet
##SRC_URI = "git://github.com/parallella/oh.git;branch=${SBRANCH};protocol=https"

SRC_URI = "git://github.com/peteasa/examples.git;protocol=https;branch=elink-redesign"
SRCREV = "1f6d5c15cafe11eaaea0ff9fbfc4e4c7e5ca0984"

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


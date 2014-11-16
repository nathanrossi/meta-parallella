SUMMARY = "Epiphany SDK"
DESCRIPTION = "Epiphany SDK, include GCC, binutils, etc for the Epiphany architecture"
HOMEPAGE = "https://github.com/adapteva/epiphany-sdk"
LICENSE = "GPLv3"

DOWNLOAD_ARCH_armv7a = "armv7l"

SRC_URI = "http://downloads.parallella.org/esdk/esdk.2014.11_linux_${DOWNLOAD_ARCH}.tar.gz"
SRC_URI[md5sum] = "cf0014ae7aca77e11f77b58c62534f2a"
SRC_URI[sha256sum] = "1bcca16b5f06393acec66a4150f68dadd7fd74afe0eeb52740327687a8dab62a"

LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

# The pre-built esdk only provides binaries for armv7 with hard-float
python () {
	if d.getVar("TUNE_ARCH", True) == "arm":
		if "callconvention-hard" not in d.getVar("TUNE_FEATURES", True):
			raise bb.parse.SkipPackage("incompatible with machine %s (pre-built esdk only works on Hard-Float ARM targets)" % d.getVar('MACHINE', True))
}

S = "${WORKDIR}/esdk.${PV}"

# Disable these for now, as these operations don't know how to handle epiphany elf files
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

do_compile() {
	:
}

do_install() {
	# Toolchain
	install -d ${D}/${bindir}
	install -d ${D}/usr/epiphany-elf/bin ${D}/usr/epiphany-elf/epiphany-elf/bin
	for i in $(ls ${S}/tools/e-gnu/bin/ | grep epiphany-elf); do
		install -m 755 ${S}/tools/e-gnu/bin/$i ${D}/usr/epiphany-elf/bin/$i
		ln -sf ../epiphany-elf/bin/$i ${D}/${bindir}/$i
	done
	cp -ar ${S}/tools/e-gnu/epiphany-elf/bin/* ${D}/usr/epiphany-elf/epiphany-elf/bin

	install -d ${D}/usr/epiphany-elf/libexec
	cp -ar ${S}/tools/e-gnu/libexec/* ${D}/usr/epiphany-elf/libexec/

	install -d ${D}/usr/epiphany-elf/lib ${D}/usr/epiphany-elf/epiphany-elf/lib
	cp -ar ${S}/tools/e-gnu/lib/* ${D}/usr/epiphany-elf/lib/
	cp -ar ${S}/tools/e-gnu/epiphany-elf/lib/* ${D}/usr/epiphany-elf/epiphany-elf/lib/

	install -d ${D}/usr/epiphany-elf/epiphany-elf/include
	cp -ar ${S}/tools/e-gnu/epiphany-elf/include/* ${D}/usr/epiphany-elf/epiphany-elf/include

	install -d ${D}/usr/epiphany-elf/share
	cp -ar ${S}/tools/e-gnu/share/* ${D}/usr/epiphany-elf/share/

	# Tools/Libs
	install -d ${D}/${bindir}
	for i in $(ls ${S}/tools/host/bin/); do
		install -m 755 ${S}/tools/host/bin/$i ${D}/${bindir}/
	done

	install -d ${D}/${libdir}/epiphany
	for i in $(ls ${S}/tools/host/lib | grep -v libe-hal.so); do
		install ${S}/tools/host/lib/$i ${D}/${libdir}/epiphany/
	done
	# create a symlink for libe-hal.so
	# TODO: Fix this to either detect the current platform, hard coded to parallella_E16G3_1GB
	ln -sf bsps/parallella_E16G3_1GB/libe-hal.so ${D}/${libdir}/epiphany/libe-hal.so

	install -d ${D}/${includedir}/epiphany
	cp -ar ${S}/tools/host/include/* ${D}/${includedir}/epiphany/

	# Hardware BSPs
	install -d ${D}/${libdir}/epiphany/bsps
	for i in $(ls ${S}/bsps/ | grep -v "current"); do
		cp -ar ${S}/bsps/$i ${D}/${libdir}/epiphany/bsps/
	done
}

PACKAGES = " \
		epiphany-toolchain \
		epiphany-bsps \
		epiphany-tools \
		epiphany-libs \
		epiphany-libs-dev \
		"

INSANE_SKIP_epiphany-toolchain += "debug-files dev-so arch staticdev"
INSANE_SKIP_epiphany-libs += "dev-so"

FILES_${PN} = ""

FILES_epiphany-toolchain += " \
		${bindir}/epiphany-elf-* \
		/usr/epiphany-elf/bin/* \
		/usr/epiphany-elf/libexec/* \
		/usr/epiphany-elf/lib/* \
		/usr/epiphany-elf/include/* \
		/usr/epiphany-elf/share/* \
		/usr/epiphany-elf/epiphany-elf/* \
		"

FILES_epiphany-bsps += " \
		${libdir}/epiphany/bsps/* \
		"

RDEPENDS_epiphany-tools += "epiphany-libs"
FILES_epiphany-tools += " \
		${bindir}/e-loader \
		${bindir}/e-read \
		${bindir}/e-write \
		${bindir}/e-hw-rev \
		${bindir}/e-reset \
		${bindir}/e-clear-shmtable \
		${bindir}/e-objcopy \
		${bindir}/e-server \
		${bindir}/e-trace-server \
		${bindir}/e-trace-dump \
		"

FILES_epiphany-libs += " \
		${libdir}/epiphany/*.so \
		"

FILES_epiphany-libs-dev += " \
		${includedir}/epiphany/* \
		"


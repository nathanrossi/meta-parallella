SUMMARY = "Epiphany SDK"
DESCRIPTION = "Epiphany SDK, include GCC, binutils, etc for the Epiphany architecture"
HOMEPAGE = "https://github.com/adapteva/epiphany-sdk"
LICENSE = "GPLv3"

DOWNLOAD_ARCH_armv7a = "armv7l"

SRC_URI = "http://downloads.parallella.org/esdk/esdk.2016.3.1_linux_${DOWNLOAD_ARCH}.tar.gz"
SRC_URI[md5sum] = "28eff10d2019fd80100dfce7f23dc215"
SRC_URI[sha256sum] = "ecb6511ec53db6d7381625f6d6d4b2ccbd4c84c50311549acafcc606bcb66527"

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

	# TODO package python file.. for now simply remove it
	rm -f ${S}/tools/e-gnu/bin/epiphany-elf-sim

	for i in $(ls ${S}/tools/e-gnu/bin/ | grep epiphany-elf); do
		install -m 755 ${S}/tools/e-gnu/bin/$i ${D}/usr/epiphany-elf/bin/$i
		ln -sf ../epiphany-elf/bin/$i ${D}/${bindir}/$i
		# TODO: Fix stripping, for now just hack the stripping of really large files
		$STRIP ${D}/usr/epiphany-elf/bin/$i
	done
	cp -r ${S}/tools/e-gnu/epiphany-elf/bin/* ${D}/usr/epiphany-elf/epiphany-elf/bin

	install -d ${D}/usr/epiphany-elf/libexec
	cp -r ${S}/tools/e-gnu/libexec/* ${D}/usr/epiphany-elf/libexec/

	# TODO: Fix stripping, for now just hack the stripping of really large files
	for i in cc1 cc1plus collect2 lto1 lto-wrapper; do
		$STRIP ${D}/usr/epiphany-elf/libexec/gcc/epiphany-elf/5.2.0/$i
	done

	install -d ${D}/usr/epiphany-elf/lib ${D}/usr/epiphany-elf/epiphany-elf/lib
	cp -r ${S}/tools/e-gnu/lib/* ${D}/usr/epiphany-elf/lib/
	cp -r ${S}/tools/e-gnu/epiphany-elf/lib/* ${D}/usr/epiphany-elf/epiphany-elf/lib/

	install -d ${D}/usr/epiphany-elf/epiphany-elf/include
	cp -r ${S}/tools/e-gnu/epiphany-elf/include/* ${D}/usr/epiphany-elf/epiphany-elf/include

	install -d ${D}/usr/epiphany-elf/share
	cp -r ${S}/tools/e-gnu/share/* ${D}/usr/epiphany-elf/share/

	# Tools/Libs
	install -d ${D}/${bindir}
	for i in $(ls ${S}/tools/host/bin/); do
		install -m 755 ${S}/tools/host/bin/$i ${D}/${bindir}/
	done

	install -d ${D}/${libdir}/epiphany
	for i in $(ls ${S}/tools/host/lib); do
		install ${S}/tools/host/lib/$i ${D}/${libdir}/epiphany/
	done

	install -d ${D}/${includedir}/epiphany
	cp -r ${S}/tools/host/include/* ${D}/${includedir}/epiphany/

	# Hardware BSPs
	install -d ${D}/${libdir}/epiphany/bsps
	for i in $(ls ${S}/bsps/ | grep -v "current"); do
		cp -r ${S}/bsps/$i ${D}/${libdir}/epiphany/bsps/
	done

	# Now create the epiphany-sdk to allow epiphany-examples to work
	mkdir -p ${D}/usr/epiphany/epiphany-sdk/tools/host
	cd ${D}/${libdir}/epiphany/bsps/
	ln -s parallella_E16G3_1GB current
	cd ${D}/${bindir}
	ln -s ../epiphany-elf/bin/epiphany-elf-addr2line e-addr2line
	ln -s ../epiphany-elf/bin/epiphany-elf-ar e-ar
	ln -s ../epiphany-elf/bin/epiphany-elf-as e-as
	ln -s ../epiphany-elf/bin/epiphany-elf-c++ e-c++
	ln -s ../epiphany-elf/bin/epiphany-elf-c++filt e-c++filt
	ln -s ../epiphany-elf/bin/epiphany-elf-cpp e-cpp
	ln -s ../epiphany-elf/bin/epiphany-elf-elfedit e-elfedit
	ln -s ../epiphany-elf/bin/epiphany-elf-g++ e-g++
	ln -s ../epiphany-elf/bin/epiphany-elf-gcc e-gcc
	ln -s ../epiphany-elf/bin/epiphany-elf-gcc-5.2.0 e-gcc-5.2.0
	ln -s ../epiphany-elf/bin/epiphany-elf-gcc-ar e-gcc-ar
	ln -s ../epiphany-elf/bin/epiphany-elf-gcc-nm e-gcc-nm
	ln -s ../epiphany-elf/bin/epiphany-elf-gcc-ranlib e-gcc-ranlib
	ln -s ../epiphany-elf/bin/epiphany-elf-gcov e-gcov
	ln -s ../epiphany-elf/bin/epiphany-elf-gcov-tool e-gcov-tool
	ln -s ../epiphany-elf/bin/epiphany-elf-gdb e-gdb
	ln -s ../epiphany-elf/bin/epiphany-elf-ld e-ld
	ln -s ../epiphany-elf/bin/epiphany-elf-ld.bfd e-ld.bfd
	ln -s ../epiphany-elf/bin/epiphany-elf-nm e-nm
	ln -s ../epiphany-elf/bin/epiphany-elf-objcopy e-objcopy
	ln -s ../epiphany-elf/bin/epiphany-elf-objdump e-objdump
	ln -s ../epiphany-elf/bin/epiphany-elf-ranlib e-ranlib
	ln -s ../epiphany-elf/bin/epiphany-elf-readelf e-readelf
	ln -s ../epiphany-elf/bin/epiphany-elf-run e-run
	ln -s ../epiphany-elf/bin/epiphany-elf-size e-size
	ln -s ../epiphany-elf/bin/epiphany-elf-strings e-strings
	ln -s ../epiphany-elf/bin/epiphany-elf-strip e-strip
	cd ${D}/usr/epiphany-elf/bin
	ln -s epiphany-elf-addr2line e-addr2line
	ln -s epiphany-elf-ar e-ar
	ln -s epiphany-elf-as e-as
	ln -s epiphany-elf-c++ e-c++
	ln -s epiphany-elf-c++filt e-c++filt
	ln -s epiphany-elf-cpp e-cpp
	ln -s epiphany-elf-elfedit e-elfedit
	ln -s epiphany-elf-g++ e-g++
	ln -s epiphany-elf-gcc e-gcc
	ln -s epiphany-elf-gcc-5.2.0 e-gcc-5.2.0
	ln -s epiphany-elf-gcc-ar e-gcc-ar
	ln -s epiphany-elf-gcc-nm e-gcc-nm
	ln -s epiphany-elf-gcc-ranlib e-gcc-ranlib
	ln -s epiphany-elf-gcov e-gcov
	ln -s epiphany-elf-gcov-tool e-gcov-tool
	ln -s epiphany-elf-gdb e-gdb
	ln -s epiphany-elf-ld e-ld
	ln -s epiphany-elf-ld.bfd e-ld.bfd
	ln -s epiphany-elf-nm e-nm
	ln -s epiphany-elf-objcopy e-objcopy
	ln -s epiphany-elf-objdump e-objdump
	ln -s epiphany-elf-ranlib e-ranlib
	ln -s epiphany-elf-readelf e-readelf
	ln -s epiphany-elf-run e-run
	ln -s epiphany-elf-size e-size
	ln -s epiphany-elf-strings e-strings
	ln -s epiphany-elf-strip e-strip
	cd ${D}/usr/epiphany/epiphany-sdk
	ln -s ../../lib/epiphany/bsps .
	cd tools
	ln -s ../../../epiphany-elf e-gnu
	cd host
	ln -s ../../../../bin .
	ln -s ../../../../include/epiphany ./include
	ln -s ../../../../lib/epiphany ./lib
}

PACKAGES = " \
		epiphany-toolchain \
		epiphany-bsps \
		epiphany-tools \
		epiphany-libs \
		epiphany-libs-dev \
		"

INSANE_SKIP_epiphany-toolchain += "debug-files dev-so arch staticdev ldflags"
INSANE_SKIP_epiphany-libs += "dev-so staticdev ldflags"
INSANE_SKIP_epiphany-tools += "ldflags"

FILES_${PN} = ""

RDEPENDS_epiphany-toolchain += "epiphany-libs"
FILES_epiphany-toolchain += " \
		${bindir}/epiphany-elf-* \
		/usr/epiphany-elf/bin/* \
		/usr/epiphany-elf/libexec/* \
		/usr/epiphany-elf/lib/* \
		/usr/epiphany-elf/include/* \
		/usr/epiphany-elf/share/* \
		/usr/epiphany-elf/epiphany-elf/* \
		${bindir}/e-addr2line \
		${bindir}/e-ar \
		${bindir}/e-as \
		${bindir}/e-c++ \
		${bindir}/e-c++filt \
		${bindir}/e-cpp \
		${bindir}/e-elfedit \
		${bindir}/e-g++ \
		${bindir}/e-gcc \
		${bindir}/e-gcc-5.2.0 \
		${bindir}/e-gcc-ar \
		${bindir}/e-gcc-nm \
		${bindir}/e-gcc-ranlib \
		${bindir}/e-gcov \
		${bindir}/e-gcov-tool \
		${bindir}/e-gdb \
		${bindir}/e-ld \
		${bindir}/e-ld.bfd \
		${bindir}/e-nm \
		${bindir}/e-objcopy \
		${bindir}/e-objdump \
		${bindir}/e-ranlib \
		${bindir}/e-readelf \
		${bindir}/e-run \
		${bindir}/e-size \
		${bindir}/e-strings \
		${bindir}/e-strip \
		/usr/epiphany/epiphany-sdk/tools/e-gnu \
		/usr/epiphany/epiphany-sdk/tools/host/bin \
		"

FILES_epiphany-bsps += " \
		${libdir}/epiphany/bsps/* \
		/usr/epiphany/epiphany-sdk/bsps \
		"

RDEPENDS_epiphany-tools += "epiphany-libs"
FILES_epiphany-tools += " \
		${bindir}/e-clear-shmtable \
		${bindir}/e-dump-regs \
		${bindir}/e-hw-rev \
		${bindir}/e-loader \
		${bindir}/e-meshdump \
		${bindir}/e-read \
		${bindir}/e-reset \
		${bindir}/e-server \
		${bindir}/e-trace-dump \
		${bindir}/e-trace-server \
		${bindir}/e-write \
		"

FILES_epiphany-libs += " \
		${libdir}/epiphany/*.so \
		${libdir}/epiphany/*.so.0 \
		${libdir}/epiphany/*.so.0.0.0 \
		${libdir}/epiphany/*.a \
		${libdir}/epiphany/*.la \
		/usr/epiphany/epiphany-sdk/tools/host/lib/* \
		"

FILES_epiphany-libs-dev += " \
		${includedir}/epiphany/* \
		/usr/epiphany/epiphany-sdk/tools/host/include/* \
		"


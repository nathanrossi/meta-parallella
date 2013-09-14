
# Override the TARGET to be "epiphany"
HOST_SYS := "${TARGET_SYS}"
HOST_PREFIX := "${TARGET_PREFIX}"
TARGET_SYS = "epiphany-elf"
TARGET_PREFIX = "epiphany-"

# Magic override to allow for canadian cross-target building
INHIBIT_DEFAULT_DEPS = "1"
BASEDEPENDS := "virtual/${HOST_PREFIX}gcc virtual/${HOST_PREFIX}compilerlibs virtual/libc"

# Use standard binutils build recipe
require recipes-devtools/binutils/binutils.inc

# Dont provide extra configure options
EXTRA_OECONF = ""

# Use the adapteva binutils sources
SRC_URI = "git://github.com/adapteva/epiphany-binutils.git;protocol=http;branch=epiphany-binutils-2_23"
SRCREV = "d209275e4bea601dac9402c63457c8653e347f2f"
S = "${WORKDIR}/git"

LIC_FILES_CHKSUM="\
	file://src-release;endline=17;md5=4830a9ef968f3b18dd5e9f2c00db2d35\
	file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552\
	file://COPYING.LIB;md5=9f604d8a4f8e74f4f5140845a21b6674\
	file://COPYING3;md5=d32239bcb673463ab874e80d47fae504\
	file://COPYING3.LIB;md5=6a6a8e020838b23406c81b19c1d46df6\
	file://gas/COPYING;md5=d32239bcb673463ab874e80d47fae504\
	file://include/COPYING;md5=59530bdf33659b29e73d4adb9f9f6552\
	file://include/COPYING3;md5=d32239bcb673463ab874e80d47fae504\
	file://libiberty/COPYING.LIB;md5=a916467b91076e631dd8edb7424769c7\
	file://bfd/COPYING;md5=d32239bcb673463ab874e80d47fae504\
	"

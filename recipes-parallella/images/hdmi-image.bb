#
# Parallella hdmi Image
#
SUMMARY = "An image that provides a complete build environment for working with the Epiphany co-processor"
LICENSE = "MIT"
DESCRIPTION = "Parallella hdmi Image"

PR="r1"

inherit core-image

IMAGE_FEATURES += "ssh-server-openssh"

# OPTION:
#
# Either use the released Epiphany SDK using:
#  packagegroup-epiphany-buildessential 
#  packagegroup-epiphany-tools
#
# Or use build the Epiphany SDK from source using:
#  packagegroup-epiphany-elf-buildessentialfromsource
#  packagegroup-epiphany-sdk-buildessentialfromsource

IMAGE_INSTALL = " \
		packagegroup-core-boot \
		${CORE_IMAGE_EXTRA_INSTALL} \
		packagegroup-core-buildessential \
		packagegroup-epiphany-elf-buildessentialfromsource \
		packagegroup-epiphany-sdk-buildessentialfromsource \
		git \
		"

IMAGE_INSTALL += "\
   ${ROOTFS_PKGMANAGE_BOOTSTRAP} \
	strace \
	ldd \
	gdbserver \
	lighttpd \
	bash \
    sudo \
    "
IMAGE_FEATURES += "package-management"

# Inherit the core-image (Causes core-image to be built)
inherit core-image

# Specify to write image as a tar.gz file
IMAGE_FSTYPES = "tar.gz"

# Add kernel-dev to the image for uapi/linux/epiphany.h so that e-hal can be built (TODO is this now not needed?)
IMAGE_INSTALL += " \
	kernel-dev \
	kernel-devsrc \
"

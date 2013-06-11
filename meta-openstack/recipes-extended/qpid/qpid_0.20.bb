DESCRIPTION = "AMQP message brokers"
HOMEPAGE = "http://qpid.apache.org/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7ab4c208aa62d92d7a03cc8e0a89c12b"
SECTION = "mq"
DEPENDS = "boost perl-native python " 
#ruby"
PR = "r0"

SRC_URI = "http://mirror.nexcess.net/apache/${PN}/${PV}/qpid-cpp-0.20.tar.gz \
           file://quick-fix.patch"

SRC_URI[md5sum] = "566132c5939ca31a32a0e80cc4124122"
SRC_URI[sha256sum] = "31b80ba3c4773d288b4e6245e3b2048438386331d460f7a7b0794cbd200301de"

S = "${WORKDIR}/${PN}c-${PV}"

inherit autotools python-dir perlnative cpan-base update-rc.d

# Env var which tells perl if it should use host (no) or target (yes) settings
export PERLCONFIGTARGET = "${@is_target(d)}"
export PERL_INC = "${STAGING_LIBDIR}${PERL_OWN_DIR}/perl/${@get_perl_version(d)}/CORE"
export PERL_LIB = "${STAGING_LIBDIR}${PERL_OWN_DIR}/perl/${@get_perl_version(d)}"
export PERL_ARCHLIB = "${STAGING_LIBDIR}${PERL_OWN_DIR}/perl/${@get_perl_version(d)}"
export PERL="${STAGING_BINDIR}/perl"

EXTRA_OECONF += " --without-help2man"

PACKAGES += "qmfgen qmfgen-python"

FILES_qmfgen = "${bindir}/qmf-gen"

FILES_qmfgen-python = "${PYTHON_SITEPACKAGES_DIR}/*"

FILES_qmfgen-python-dbg += "${PYTHON_SITEPACKAGES_DIR}/.debug/*"

FILES_${PN} += "${libdir}/${PN}/tests/test_store.so"

FILES_${PN}-dev += "${libdir}/${PN}/tests/test_store.so "

FILES_${PN}-dbg += "${libdir}/${PN}/${PN}/tests/.debug/* \
                    ${libdir}/${PN}/tests/.debug/*  \
                    ${libdir}/${PN}/daemon/.debug/*"

FILES_${PN}-doc += "${datadir}/qpidc/*"

INITSCRIPT_NAME = "qpidd"
INITSCRIPT_PARAMS = "defaults"
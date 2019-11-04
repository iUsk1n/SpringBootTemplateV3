var SBT = SBT || {};

SBT.namespace = function(ns_string) {
    var parts = ns_string.split('.');
    var parent = SBT;

    if (parts[0] === 'SBT') {
        parts = parts.slice(1);
    }

    for (var i = 0; i < parts.length; i++) {
        if (typeof parent[parts[i]] === 'undefined') {
            parent[parts[i]] = {};
        }
        parent = parent[parts[i]];
    }
};
